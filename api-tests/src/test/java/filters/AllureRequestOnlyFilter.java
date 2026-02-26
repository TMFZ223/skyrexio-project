package filters;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/*
Класс фильтра для allure, который позволяет прикрепить к шагу запроса только информацию, которая относится к этому запросу.
То, что возвращается в ответе в шаг не попадает, но отображается в консоле.
Это можно увидеть при запуске в IntelliJ IDEA и в терминале
 */
public class AllureRequestOnlyFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext context) {
        String requestLine = requestSpec.getMethod() + " " + requestSpec.getURI();

        String headers = requestSpec.getHeaders().asList().stream()
                .map(h -> h.getName() + ": " + h.getValue())
                .collect(Collectors.joining("\n"));
        String body = extractBody(requestSpec);
        StringBuilder sb = new StringBuilder()
                .append(requestLine).append("\n")
                .append(headers);
        if (body != null && !body.isEmpty()) {
            sb.append("\n\n").append(body);
        }

        // Один аттачмент в шаг Allure: только запрос
        Allure.addAttachment("HTTP Request", "text/plain", sb.toString());

        // Продолжаем цепочку фильтров: ответ НЕ аттачим
        return context.next(requestSpec, responseSpec);
    }

    private String extractBody(FilterableRequestSpecification req) {
        Object body = req.getBody();
        if (body == null) return null;
        if (body instanceof byte[]) {
            return new String((byte[]) body, StandardCharsets.UTF_8);
        }
        return body.toString();
    }
}