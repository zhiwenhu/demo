package com.example.demo.controls;

import com.example.demo.constants.ElasticsearchConstant;
import com.example.demo.entity.UserBean;
import com.example.demo.enums.Gender;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class EsController {
    @Autowired
    private PersonService personService;

    @GetMapping("/elasticSearch")
    public List doElasticSearch() {
//        personService.deleteIndex(ElasticsearchConstant.INDEX_NAME);
//        personService.createIndex(ElasticsearchConstant.INDEX_NAME);
//        List<Person> list = new ArrayList<>();
//        list.add(Person.builder().age(11).birthday(new Date()).country("CN").id(1L).name("lilei").remark("test1").build());
//        list.add(Person.builder().age(22).birthday(new Date()).country("US").id(2L).name("lucy").remark("test2").build());
//        list.add(Person.builder().age(33).birthday(new Date()).country("India").id(3L).name("fhuasda").remark("test3").build());
//
//        personService.insert(ElasticsearchConstant.INDEX_NAME, list);
//
//        Person person = Person.builder().age(33).birthday(new Date()).country("ID_update").id(3L).name("呵呵update").remark("test3_update").build();
//        List<Person> list1 = new ArrayList<>();
//        list1.add(person);
//        personService.update(ElasticsearchConstant.INDEX_NAME, list1);
//
//        personService.delete(ElasticsearchConstant.INDEX_NAME, Person.builder().id(1L).build());
        List<Person> personList = personService.searchList(ElasticsearchConstant.INDEX_NAME);
        System.out.println(personList);

        return personList;
    }
}
