package com.tommy.white_jotter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Table(name = "admin_permission")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Admin_permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "tname")
    private String tname;
    @Column(name = "url")
    private String url;
}
