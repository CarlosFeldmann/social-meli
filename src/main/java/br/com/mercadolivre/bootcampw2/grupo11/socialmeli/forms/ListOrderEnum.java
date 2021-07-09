package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;


import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public enum ListOrderEnum {
    name_asc(Sort.sort(Customer.class).by(Customer::getUserName).ascending()),
    name_desc(Sort.sort(Customer.class).by(Customer::getUserName).descending());

    private final Sort sort;
}
