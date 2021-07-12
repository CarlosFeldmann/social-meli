package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.forms;

import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public enum ListOrderEnum {
  name_asc(Sort.sort(User.class).by(User::getUserName).ascending()),
  name_desc(Sort.sort(User.class).by(User::getUserName).descending());

  private final Sort sort;
}
