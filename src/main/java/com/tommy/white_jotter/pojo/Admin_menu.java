package com.tommy.white_jotter.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "admin_menu")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Admin_menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private String name;
    private String nameZh;
    private String iconCls;
    private String component;
    private Long parentId;

    @Transient
    private List<Admin_menu> children;

}
