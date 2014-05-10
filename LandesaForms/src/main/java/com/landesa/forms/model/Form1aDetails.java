package com.landesa.forms.model;

import android.os.Bundle;

public class Form1aDetails {
    public static final String NAME_OF_HOUSEHOLD_KEY = "com.landesa.forms.nameOfHousehold";
    public static final String NAME_OF_WIFE_KEY = "com.landesa.forms.nameOfWife";
    public static final String NAME_OF_FATHER_OR_HUSBAND_KEY = "com.landesa.forms.nameOfFatherOrHusband";
    public static final String SEX_KEY = "com.landesa.forms.sex";
    public static final String CASTE_KEY = "com.landesa.forms.caste";
    public static final String WIDOW_KEY = "com.landesa.forms.widow";
    public static final String ABANDONED_KEY = "com.landesa.forms.abandoned";
    public static final String DIVORCEE_KEY = "com.landesa.forms.divorcee";
    public static final String UW_KEY = "com.landesa.forms.uw";
    public static final String DISABLE_KEY = "com.landesa.forms.disable";

    private String mNameOfHousehold;
    private String mNameOfWife;
    private String mFatherOrHusbandName;
    private String mSex;
    private String mCaste;
    private int mSingleWidow;
    private int mSingleAbandoned;
    private int mSingleDivorcee;
    private int mSingleUWAbove30;
    private int mSingleDisable;

    public Form1aDetails(String nameOfHousehold, String nameOfWife, String fatherOrHusbandName,
        String sex, String caste, int widow, int abandoned, int divorcee, int uw, int disable) {
        mNameOfHousehold = nameOfHousehold;
        mNameOfWife = nameOfWife;
        mFatherOrHusbandName = fatherOrHusbandName;
        mSex = sex;
        mCaste = caste;
        mSingleWidow = widow;
        mSingleAbandoned = abandoned;
        mSingleDivorcee = divorcee;
        mSingleUWAbove30 = uw;
        mSingleDisable = disable;
    }
}
