package com.degiro.portfolio.responseobject;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortFolioObject
{
    Integer userId;
    LocalDateTime tillDate;
    Double amount;

}
