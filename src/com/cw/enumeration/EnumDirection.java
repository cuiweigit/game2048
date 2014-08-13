package com.cw.enumeration;

public enum EnumDirection {
    /**
     * 未移动
     */
    _O("O", 0x0000, "o"),
    /**
     * 向左
     */
    _LEFT("LEFT", 0x0001, "left"),
    /**
     * 向右
     */
    _RIGHT("RIGHT", 0x0102, "right"),
    /**
     * 向上
     */
    _UP("UP", 0x0003, "up"),
    /**
     * 向下
     */
    _DOWN("DOWN", 0x0004, "down");

    private EnumDirection(String name, Integer iValue, String sValue) {
        this.name = name;
        this.iValue = iValue;
        this.sValue = sValue;
    }

    private String name;
    private Integer iValue;
    private String sValue;

    public String getName() {
        return name;
    }

    public Integer getiValue() {
        return iValue;
    }

    public String getsValue() {
        return sValue;
    }

}
