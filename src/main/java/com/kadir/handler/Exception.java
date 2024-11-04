
package com.kadir.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exception<E> {

    private String path;

    private Date createdTime;

    private String hostName;

    private E message;
}
