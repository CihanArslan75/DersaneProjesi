package com.hokumus.course.management.model.yonetim;

public enum KarZarar {
	NULL,
	KAR,
	ZARAR;
	
	 public String toString(){
        switch(this) {
            case KAR:
                return "KAR";
            case ZARAR:
                return "ZARAR";
            default: return " ";
        }
	 }
}
