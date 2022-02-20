package com.degiro.portfolio.responseobject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortFolioObject
{
    Integer userId;
    LocalDateTime tillDate;
    Double amount;

}
