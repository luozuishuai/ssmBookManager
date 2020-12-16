package com.shangma.cn.entity;

public class Booktype {
    private Integer typeId;

    private Integer parentId;

    private String typeName;

    @Override
    public String toString() {
        return "Booktype{" +
                "typeId=" + typeId +
                ", parentId=" + parentId +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}