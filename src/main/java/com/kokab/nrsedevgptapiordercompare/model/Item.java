package com.kokab.nrsedevgptapiordercompare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}
