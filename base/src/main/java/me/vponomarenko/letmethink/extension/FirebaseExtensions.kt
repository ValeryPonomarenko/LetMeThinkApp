package me.vponomarenko.letmethink.extension


/**
 * Author: Valery Ponomarenko
 * Date: 09/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */
//
//fun<T> DatabaseReference.asSingle(convertFun: (DataSnapshot) -> T): Single<T> =
//        Single.create { emitter ->
//            this.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onCancelled(error: DatabaseError) {
//                    emitter.onError(error.toException())
//                }
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    emitter.onSuccess(convertFun(snapshot))
//                }
//            })
//        }