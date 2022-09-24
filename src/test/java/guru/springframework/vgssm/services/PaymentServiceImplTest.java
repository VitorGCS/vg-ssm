package guru.springframework.vgssm.services;

import guru.springframework.vgssm.domain.Payment;
import guru.springframework.vgssm.domain.PaymentEvent;
import guru.springframework.vgssm.domain.PaymentState;
import guru.springframework.vgssm.repository.PaymentRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setup(){
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        System.out.println("Should be NEW");
        System.out.println(savedPayment.getState());

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());
        Payment preAuthedPayment =paymentRepository.getOne(savedPayment.getId());

        System.out.println("Should be PRE_AUTH or PRE_AUTH_ERROR");
        System.out.println(sm.getState().getId());
        System.out.println(preAuthedPayment);
    }
}