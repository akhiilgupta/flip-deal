package com.flipdeal.service;

import com.flipdeal.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final ProductService productService;

    private final ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        productService.createPromotionSet(args[0]);
        log.info("terminating application after creating output.json file");
        context.close();
    }
}
