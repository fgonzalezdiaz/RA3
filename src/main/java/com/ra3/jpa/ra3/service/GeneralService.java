package com.ra3.jpa.ra3.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.CreateOrderRequestDto;
import com.ra3.jpa.ra3.dto.CreateOrderResponseDto;
import com.ra3.jpa.ra3.dto.CustomerAddressDto;
import com.ra3.jpa.ra3.dto.CustomerDto;
import com.ra3.jpa.ra3.dto.OrderItemDto;
import com.ra3.jpa.ra3.dto.RoleDto;
import com.ra3.jpa.ra3.dto.UserCustomerDto;
import com.ra3.jpa.ra3.dto.UserDto;
import com.ra3.jpa.ra3.dto.UsuariRolesDto;
import com.ra3.jpa.ra3.mapper.CustomerMapper;
import com.ra3.jpa.ra3.mapper.UserCustomerMapper;
import com.ra3.jpa.ra3.mapper.UserMapper;
import com.ra3.jpa.ra3.model.Address;
import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.model.OrderItem;
import com.ra3.jpa.ra3.model.OrderStatus;
import com.ra3.jpa.ra3.model.Product;
import com.ra3.jpa.ra3.model.Role;
import com.ra3.jpa.ra3.model.User;
import com.ra3.jpa.ra3.repository.AddressRepository;
import com.ra3.jpa.ra3.repository.CustomerRepository;
import com.ra3.jpa.ra3.repository.OrderItemRepository;
import com.ra3.jpa.ra3.repository.OrderRepository;
import com.ra3.jpa.ra3.repository.ProductRepository;
import com.ra3.jpa.ra3.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class GeneralService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    
    @Transactional
    public UserDto createUserCustomer(UserCustomerDto userCustomerDto)throws Exception{
        UserDto userDto = UserCustomerMapper.userCustomerToUserDto(userCustomerDto);
        CustomerDto customerDto = UserCustomerMapper.userCustomerToCustomerDto(userCustomerDto);

        // Conversion de UserDto to User (Entity)
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
            
        // Conversion de CustomerDto to Customer (Entity)
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
          
        userRepository.save(user);
        customerRepository.save(customer);


        return UserMapper.toDto(user);
    }

    public UserCustomerDto findAllInfoUser(Long id){
        User user = (userRepository.findById(id)).get();
        Customer customer = (customerRepository.findById(id)).get();

        UserDto userDto = UserMapper.toDto(user);
        CustomerDto customerDto = CustomerMapper.toDto(customer);

        UserCustomerDto userCustomerDto = new UserCustomerDto(userDto.getEmail(), userDto.getPassword(), customerDto.getFirstName() , customerDto.getLastName(), customerDto.getPhone());

        return userCustomerDto;
    }

    @Transactional
    public String deleteCustomerAddress(Long id){
        if((customerRepository.findById(id)).isPresent()){            
            addressRepository.deleteByCustomerId(id);
            return "Borrados correctamente";

        } else {
            return "Este customer no existe";
        }
    }
    
    @Transactional
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto dto) throws Exception {
        Customer customer = customerRepository.findById(dto.getIdCustomer())
            .orElseThrow(() -> new Exception("Customer amb id " + dto.getIdCustomer() + " no trobat"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(dto.getOrderDate());
        order.setOrderStatus(OrderStatus.PENDENT);
        order.setStatus(true);
        order.setDataCreated(new Date(System.currentTimeMillis()));
        orderRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItemDto> items = new ArrayList<>();

        for (Long productId : dto.getIdProductes()) {
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Producte amb id " + productId + " no trobat"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(1L);
            item.setUnitPrice(product.getPrice());
            orderItemRepository.save(item);

            totalAmount = totalAmount.add(product.getPrice());

            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setOrderId(order.getId());
            itemDto.setProductId(product.getId());
            itemDto.setQuantity(1L);
            itemDto.setUnitPrice(product.getPrice());
            items.add(itemDto);
        }

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        CreateOrderResponseDto response = new CreateOrderResponseDto();
        response.setId(order.getId());
        response.setCustomerId(customer.getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderStatus(order.getOrderStatus());
        response.setItems(items);

        return response;
    }

    @Transactional
    public CreateOrderResponseDto processOrder(Long id) throws Exception {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new Exception("Order amb id " + id + " no trobada"));

        if (order.getOrderStatus() != OrderStatus.PENDENT) {
            throw new Exception("L'order amb id " + id + " no es troba en estat PENDENT");
        }

        order.setOrderStatus(OrderStatus.PROCESSAT);
        order.setDataUpdated(new Date(System.currentTimeMillis()));
        orderRepository.save(order);

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.getId());
        List<OrderItemDto> items = new ArrayList<>();
        for (OrderItem oi : orderItems) {
            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setOrderId(order.getId());
            itemDto.setProductId(oi.getProduct().getId());
            itemDto.setQuantity(oi.getQuantity());
            itemDto.setUnitPrice(oi.getUnitPrice());
            items.add(itemDto);
        }

        CreateOrderResponseDto response = new CreateOrderResponseDto();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderStatus(order.getOrderStatus());
        response.setItems(items);

        return response;
    }

    public List<CustomerAddressDto> findAllCustomerAddress(){
        List<Customer> customers = customerRepository.findAll();
        
        List<CustomerAddressDto> lista = new ArrayList<CustomerAddressDto>();
        
        for(int i = 0; i < customers.size(); i++){
            Customer c = customers.get(i);
            Address adr = addressRepository.findByCustomerId(c.getId());

            CustomerAddressDto customerAddressDto = new CustomerAddressDto();
            customerAddressDto.setFirstName(c.getFirstName());
            customerAddressDto.setLastName(c.getLastName());
            customerAddressDto.setPhone(c.getPhone());

            if(adr != null){
                customerAddressDto.setAddress(adr.getAddress());
                customerAddressDto.setCity(adr.getCity());
                customerAddressDto.setPostalCode(adr.getPostalCode());
                customerAddressDto.setCountry(adr.getCountry());
            }


            lista.add(customerAddressDto);            
        }

        return lista;
    }

    @Transactional
    public UsuariRolesDto eliminaRolesUsuari(Long id, List<Long> idRoles){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UsuariRolesDto usuariRolesDto = new UsuariRolesDto();
            usuariRolesDto.setEmail(user.getEmail());
            
            user.getRoles().removeIf(role -> idRoles.contains(role.getId()));
            userRepository.save(user);

            List<Role> roles = user.getRoles();
            List<RoleDto> rolesDto = new ArrayList<RoleDto>();
            for(Role role : roles){
                RoleDto roleDto = new RoleDto();
                roleDto.setName(role.getName());
                roleDto.setDescription(role.getDescription());

                rolesDto.add(roleDto);
            }
            

            usuariRolesDto.setRoles(rolesDto);

            return usuariRolesDto;
        } else {
            return null;
        }
    }
}
