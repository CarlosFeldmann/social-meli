//package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.services;
//
//import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Outro;
//import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.entities.Test;
//import br.com.mercadolivre.bootcampw2.grupo11.socialmeli.repositories.TestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TestService {
//
//    @Autowired
//    private TestRepository repository;
//
//
//    public void test() {
//        repository.save(new Test("bbb"));
//
//        var bbb = repository.findFirstByName("bbb");
//
//        bbb.getOutros().add(new Outro());
//
//        repository.save(bbb);
//    }
//}
