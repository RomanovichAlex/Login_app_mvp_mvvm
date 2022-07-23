package by.romanovich.login_app_mvp_mvvm.domain

import by.romanovich.login_app_mvp_mvvm.domain.entities.UserProfile

// CRUD
interface UserRepo {
    // Create
    fun addUser(user: UserProfile)

    // Read возращает список UserProfile
    fun getAllUsers(): List<UserProfile>

    // Update передаем нового пользователя обновить
    fun changeUser(id: String, user: UserProfile)

    // Delete
    //удаляет пользователя
    fun deleteUser(id: String)
    fun deleteAll()
}