package com.wssearch.controller;

import lombok.*;

/**
 * @author lzb
 * @date 2019/1/17 21:25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WSInfoBrief {
    private String ah;
    private String ajlx;
    private String spcx;

    @Override
    public String toString() {
        return "WSInfoBrief{" +
                "ah='" + ah + '\'' +
                ", ajlx='" + ajlx + '\'' +
                ", spcx='" + spcx + '\'' +
                '}';
    }
}
