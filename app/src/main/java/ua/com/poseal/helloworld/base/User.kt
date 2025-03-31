package ua.com.poseal.helloworld.base

import com.github.javafaker.Faker
import java.util.Random

data class User(
    val id: Long,
    val photoUrl: String,
    val name: String,
    val status: String,
)

fun createUserList(): List<User> {
    val faker = Faker.instance(Random(0))
    val images = mutableListOf(
        "https://gravatar.com/avatar/97d51388277edd7e77d91e87e58eaee7?s=400&d=robohash&r=x",
        "https://robohash.org/97d51388277edd7e77d91e87e58eaee7?set=set4&bgset=&size=400x400",
        "https://gravatar.com/avatar/5ba0cadd268c255e0d9c0a376bd4d9d9?s=400&d=robohash&r=x",
        "https://robohash.org/5ba0cadd268c255e0d9c0a376bd4d9d9?set=set4&bgset=&size=400x400",
        "https://gravatar.com/avatar/3bbf922d126edda4356b90451a01c09f?s=400&d=robohash&r=x",
        "https://robohash.org/1bf2dd460870432fa81c4ee611c7c174?set=set4&bgset=&size=400x400",
        "https://gravatar.com/avatar/1bf2dd460870432fa81c4ee611c7c174?s=400&d=robohash&r=x",
        "https://robohash.org/8c614a23bdcc683b3ad49cc447ccccc6?set=set4&bgset=&size=400x400",
        "https://gravatar.com/avatar/8c614a23bdcc683b3ad49cc447ccccc6?s=400&d=robohash&r=x",
        "https://robohash.org/632c547eff26ad57b28fc6f72e9222cc?set=set4&bgset=&size=400x400",
    )

    val list = List(100) { index ->
        val id = index + 1L
        User(
            id = id,
            photoUrl = images[index % images.size],
            name = faker.name().fullName(),
            status = faker.shakespeare().hamletQuote()
        )
    }
    return list
}





















