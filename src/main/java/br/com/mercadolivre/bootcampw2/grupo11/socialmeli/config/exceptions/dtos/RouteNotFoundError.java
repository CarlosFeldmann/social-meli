package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config.exceptions.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class RouteNotFoundError extends ApiError {
    private String route;

    public RouteNotFoundError(String route) {
        super("route_not_found", "Unable to find route!", HttpStatus.NOT_FOUND.value());
        this.route = route;
    }
}
