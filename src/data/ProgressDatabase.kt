package com.androiddevs.data

import com.androiddevs.data.collections.Exercise
import com.androiddevs.data.collections.ProgressEntry
import com.androiddevs.data.collections.User
import com.androiddevs.security.checkHashForPassword
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.eq

private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("ProgressDatabase")
private val users = database.getCollection<User>()

private val progressEntries = database.getCollection<ProgressEntry>()
private val exercises = database.getCollection<Exercise>()

suspend fun registerUser(user: User): Boolean {
    return users.insertOne(user).wasAcknowledged()
}

suspend fun checkIfUserExists(email: String): Boolean {
    return users.findOne(User::email eq email) != null
}

suspend fun checkPasswordForEmail(email: String, passwordToCheck: String): Boolean {
    val actualPassword = users.findOne(User::email eq email)?.password ?: return false
    return checkHashForPassword(passwordToCheck, actualPassword)
}

suspend fun addProgressEntry(entry: ProgressEntry): Boolean {
    return progressEntries.insertOne(entry).wasAcknowledged()
}

suspend fun getProgressForExercise(owner: String, exerciseId: String): List<ProgressEntry> {
    return progressEntries.find(ProgressEntry::owner eq owner, ProgressEntry::exerciseId eq exerciseId).toList()
}

suspend fun deleteProgressEntry(id: String): Boolean {
    return progressEntries.deleteOneById(id).wasAcknowledged()
}

suspend fun getAllExercises(): List<Exercise> {
    return exercises.find().toList()
}

suspend fun getExerciseById(id: String): Exercise? {
    return exercises.findOneById(id)
}
