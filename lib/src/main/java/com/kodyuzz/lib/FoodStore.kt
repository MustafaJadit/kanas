package com.kodyuzz.lib

class FoodStore : Production<Food> {
    override fun produce(): Food {
        println("Produce food")
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun produce(): FastFood {
        println("Produce fast food")
        return FastFood()
    }
}

class InOutBurger : Production<Burger> {
    override fun produce(): Burger {
        println("Produce burger")
        return Burger()
    }
}

interface Production<out T> {
    fun produce(): T

}


interface Consumer<in T> {
    fun consume(item: T)
}

class Man : Consumer<Food> {
    override fun consume(item: Food) {
        println("people")
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        println("modern")
    }
}

class American : Consumer<Burger> {
    override fun consume(item: Burger) {
        println("American")
    }
}

open class Food
open class FastFood : Food()
class Burger : FastFood()

fun main() {
//    var food: Production<Food> = FoodStore()
//    var fastFood: Production<Food> = FastFoodStore()
//    food.produce()
//    fastFood.produce()


}