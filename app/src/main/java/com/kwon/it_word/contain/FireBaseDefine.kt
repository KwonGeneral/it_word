package com.kwon.it_word.contain

class FireBaseDefine {
    companion object {
        // FireStore & FCM
        const val FIREBASE_STORE_COLLECTION_USER:String = "User"
        const val FIREBASE_STORE_DATA_FCM_TOKEN:String = "fcm_token"
        const val FIREBASE_STORE_DATA_PHONE_MODEL:String = "phone_model"
        const val FIREBASE_STORE_DATA_CREATED_AT:String = "created_at"
        const val FIREBASE_FCM_CREATING:String = "FCM 토큰 저장 중..."
        const val FIREBASE_FCM_CREATE_SUCCESS:String = "FCM 토큰을 Firebase DB에 저장했습니다"
        const val FIREBASE_FCM_CREATE_FAIL:String = "FCM 토큰을 Firebase DB 저장에 실패했습니다"
    }
}