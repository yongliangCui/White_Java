package com.tommy.white_jotter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Table(name = "admin_role")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin_role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "姓名不能为空！")
    private String name;
    private String name_zh;
    private String enabled;
    private List<Admin_menu> menus;
    private List<Admin_permission> permissions;
}
