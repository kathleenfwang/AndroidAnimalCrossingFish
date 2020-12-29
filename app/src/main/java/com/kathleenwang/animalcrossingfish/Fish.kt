package com.kathleenwang.animalcrossingfish

class Fish(val name: String, val price: Int, val availability: String, val imageSrc: String) {
    override fun toString(): String {
        return "Fish(name='$name', price=$price, availability='$availability', imageSrc='$imageSrc')"
    }
}