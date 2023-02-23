package org.acme.logintercept;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Locale;

@RequestScoped
@Logged
public class RSBeanInterceptedExample {

    public String echoReverse(String input) {
        StringBuilder inputSB = new StringBuilder();
        StringBuilder reverse = inputSB.append(input).reverse();
        return reverse.toString().toUpperCase(Locale.ROOT);
    }

    public String echoReverse2(String input) {
        String reverse = echoReverse(input);
        return echoReverse(reverse);
    }

}
