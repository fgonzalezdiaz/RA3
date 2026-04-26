package com.ra3.jpa.ra3.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra3.jpa.ra3.dto.AddressDto;
import com.ra3.jpa.ra3.dto.CreateOrderRequestDto;
import com.ra3.jpa.ra3.dto.CreateOrderResponseDto;
import com.ra3.jpa.ra3.dto.CustomerAddressDto;
import com.ra3.jpa.ra3.dto.CustomerDto;
import com.ra3.jpa.ra3.dto.OrderItemDto;
import com.ra3.jpa.ra3.dto.RoleDto;
import com.ra3.jpa.ra3.dto.UserCustomerDto;
import com.ra3.jpa.ra3.dto.UserDto;
import com.ra3.jpa.ra3.dto.UsuariRolesDto;
import com.ra3.jpa.ra3.model.Address;
import com.ra3.jpa.ra3.model.Customer;
import com.ra3.jpa.ra3.model.Order;
import com.ra3.jpa.ra3.model.OrderItem;
import com.ra3.jpa.ra3.model.Role;
import com.ra3.jpa.ra3.model.OrderStatus;
import com.ra3.jpa.ra3.model.Product;
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
    public UserDto createUserCustomer(UserCustomerDto userCustomerDto) throws Exception {
        User user = new User();
        user.setEmail(userCustomerDto.getEmail());
        user.setPassword(userCustomerDto.getPassword());

        Customer customer = new Customer();
        customer.setFirstName(userCustomerDto.getFirstName());
        customer.setLastName(userCustomerDto.getLastName());
        customer.setPhone(userCustomerDto.getPhone());

        userRepository.save(user);
        customerRepository.save(customer);

        return UserDto.toDto(user);
    }

    public UserCustomerDto findAllInfoUser(Long id) {
        User user = userRepository.findById(id).get();
        Customer customer = customerRepository.findById(id).get();

        UserDto userDto = UserDto.toDto(user);
        CustomerDto customerDto = CustomerDto.toDto(customer);

        return new UserCustomerDto(userDto.getEmail(), userDto.getPassword(),
                customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhone());
    }

    @Transactional
    public String deleteCustomerAddress(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            addressRepository.deleteByCustomerId(id);
            return "Borrados correctamente";
        }
        return "Este customer no existe";
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
            items.add(OrderItemDto.toDto(item));
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
            items.add(OrderItemDto.toDto(oi));
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

    public List<CustomerAddressDto> findAllCustomerAddress() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerAddressDto> lista = new ArrayList<>();

        for (Customer c : customers) {
            List<Address> addresses = addressRepository.findByCustomerId(c.getId());
            List<AddressDto> addressDtos = new ArrayList<>();
            for (Address a : addresses) {
                addressDtos.add(AddressDto.toDto(a));
            }

            CustomerAddressDto customerAddressDto = new CustomerAddressDto();
            customerAddressDto.setFirstName(c.getFirstName());
            customerAddressDto.setLastName(c.getLastName());
            customerAddressDto.setPhone(c.getPhone());
            customerAddressDto.setAddreses(addressDtos);

            lista.add(customerAddressDto);
        }

        return lista;
    }

    @Transactional
    public UsuariRolesDto eliminaRolesUsuari(Long id, List<Long> idRoles) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        user.getRoles().removeIf(role -> idRoles.contains(role.getId()));
        userRepository.save(user);

        List<RoleDto> rolesDto = new ArrayList<>();
        for (Role r : user.getRoles()) {
            rolesDto.add(RoleDto.toDto(r));
        }

        UsuariRolesDto usuariRolesDto = new UsuariRolesDto();
        usuariRolesDto.setEmail(user.getEmail());
        usuariRolesDto.setRoles(rolesDto);

        return usuariRolesDto;
    }

}
