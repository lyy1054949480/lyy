package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String name;
	private int age;
	private boolean male;
	private String classNumber;
	private int score;
}
