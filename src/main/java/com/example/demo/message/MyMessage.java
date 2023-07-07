package com.example.demo.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyMessage implements Serializable {
    private static final long serialVersionUID = 3003947025776325732L;
    private String message;
}
