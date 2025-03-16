package com.rabbitmp.springboot_rabbitmq.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String message;
    private String status;
}
