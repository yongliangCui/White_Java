package com.tommy.white_jotter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Table(name = "jotter_article")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class jotter_article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "文章标题不能为空！")
    @Column(name = "article_title")
    private String articleTitle;
    @Column(name = "article_content_html")
    private String articleContentHtml;
    @Column(name = "article_content_md")
    private String articleContentMd;
    @Column(name = "article_abstract")
    private String articleAbstract;
    @Column(name = "article_cover")
    private String articleCover;
    @Column(name = "article_date")
    private String articleDate;
}
