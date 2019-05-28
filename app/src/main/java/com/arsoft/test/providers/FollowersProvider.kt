package com.arsoft.test.providers

import android.os.Handler
import com.arsoft.test.models.FollowerModel
import com.arsoft.test.presenters.FollowersPresenter

class FollowersProvider(var presenter: FollowersPresenter) {

    fun loadFollowers() {
        Handler().postDelayed({
            val followersList = ArrayList<FollowerModel>()
            followersList.add(FollowerModel("Avdotya", "Raskolnkova", "https://pp.userapi.com/c851216/v851216260/102256/YUsMBVCLGIU.jpg", 1000000))
            followersList.add(FollowerModel("Max", "Redkin", "https://pp.userapi.com/c844321/v844321294/8b2cf/QvUtuwmVW7E.jpg", 1529))
            followersList.add(FollowerModel("Ilnur", "Sadreev", "https://pp.userapi.com/c627919/v627919459/1cd2c/f5MlNluwak0.jpg", 1756))
            followersList.add(FollowerModel("Orlando", "Bloom", "https://thumbs.dfs.ivi.ru/storage37/contents/1/2/070d3bdf5de88dba04e2f4ba2bede8.jpg", 125))
            followersList.add(FollowerModel("Willem", "Dafoe", "https://st.kp.yandex.net/images/actor_iphone/iphone360_9274.jpg", 356))
            followersList.add(FollowerModel("Vin", "Diesel", "https://gif.cmtt.space/3/paper-media/7c/e9/70/8a6e9b24f617ad.jpg", 1488))
            followersList.add(FollowerModel("Dwayne", "Johnson", "https://24smi.org/public/media/235x307/celebrity/2016/07/08/1467968641-duejn-dzhonson.jpg", 356))
            followersList.add(FollowerModel("Scarlett", "Johansson", "https://st.kp.yandex.net/images/actor_iphone/iphone360_3903.jpg", 59))
            followersList.add(FollowerModel("Benedict", "Cumberbatch", "https://24smi.org/public/media/2018/10/23/a728.png", 228))
            followersList.add(FollowerModel("Leonardo", "DiCaprio", "https://hochu.ua/thumbnails/articles/cropr_640x490/90800_0.jpg", 854))
            followersList.add(FollowerModel("Samuel", "L. Jackson", "https://memepedia.ru/wp-content/uploads/2017/06/%D1%87%D1%82%D0%BE-%D1%8D%D1%82%D0%BE%D1%82-%D0%BD%D0%B8%D0%B3%D0%B5%D1%80-%D1%81%D0%B5%D0%B1%D0%B5-%D0%BF%D0%BE%D0%B7%D0%B2%D0%BE%D0%BB%D1%8F%D0%B5%D1%82.jpg", 596))
            followersList.add(FollowerModel("Cassidy", "Jones", "http://www.couturesalonstudios.com/wp-content/uploads/2018/07/7-Cassidy.jpg", 356))

            presenter.followersLoaded(followersList)
        }, 2000)
    }
}