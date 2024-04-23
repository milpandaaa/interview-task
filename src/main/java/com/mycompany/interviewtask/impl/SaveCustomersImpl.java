package com.mycompany.interviewtask.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.interviewtask.domain.CustomerDomain;
import com.mycompany.interviewtask.mapper.CustomerMapper;
import com.mycompany.interviewtask.model.Customer;
import com.mycompany.interviewtask.repository.CustomerRepository;
import com.mycompany.interviewtask.utils.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveCustomersImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDomain> saveToFileNameByPath(String toPath, String fromPath) {
        // Здесь мы парсим
        ObjectMapper objectMapper = new ObjectMapper();
        List<Customer> customer;
        try {
            customer = objectMapper.readValue(new File(fromPath), new TypeReference<>() {});
        } catch (Throwable e) {
            throw new RuntimeException("File not found");
        }
        // Здесь мы отбираем телефонные номера
        List<String> numbers = getPhoneNumber(customer);

        sortedNumber(numbers);

        // Считаем рейтинг покупателей
        setRating(customer);

        List<CustomerDomain> domain = new ArrayList<>();
        // А здесь мы все сохраняем в БД
        for (Customer item : customer) {
            CustomerDomain e = customerMapper.toDomain(item);
            customerRepository.save(e);
            domain.add(e);
        }

        // Сохраняем базу телефонным номеров в файл
        savePhoneNumbersToFile(toPath, numbers);
        return domain;
    }

    private static List<String> getPhoneNumber(List<Customer> customer) {
        List<String> numbers = new ArrayList<>();
        for (Customer value : customer) {
            String number = value.getPhoneNumber();
            if (number.startsWith("+7")) {
                continue;
            }
            numbers.add(number);
        }
        return numbers;
    }

    private static void sortedNumber(List<String> numbers) {
        // Сортируем телефонные номера классическим пузырьком, алгоритм не самый быстрый, но выбор в сторону простоты
        boolean isSorted = false;
        String buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < numbers.size() - 1; i++) {
                if (numbers.get(i).compareTo(numbers.get(i + 1)) > 0) {
                    isSorted = false;
                    buf = numbers.get(i);
                    numbers.set(i, numbers.get(i + 1));
                    numbers.set(i + 1, buf);
                }
            }
        }
    }

    private static void setRating(List<Customer> customer) {
        for (Customer item : customer) {
            int rating = item.getNumberOfPurchases() - item.getNumberOfReturns();
            if (Status.GOLD.getName().equals(item.getStatus())) {
                rating = rating + 100;
            }
            else
                if (Status.PLUTINUM.getName().equals(item.getStatus())) {
                    rating = rating + 1000;
                }
                else
                    if (Status.SILVER.getName().equals(item.getStatus())) {
                        rating = rating + 10;
                    }
            item.setRating(rating);
        }
    }

    private static void savePhoneNumbersToFile(String toPath, List<String> numbers) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(toPath);
        } catch (Exception e) {
        }
        for (String number : numbers) {
            writer.print(number + "\n");
        }
        writer.close();
    }
}
