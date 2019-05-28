package com.arsoft.test.providers

import android.os.Handler
import com.arsoft.test.models.DialogModel
import com.arsoft.test.models.MessageModel
import com.arsoft.test.presenters.DialogsPresenter

class DialogsProvider(var presenter: DialogsPresenter) {

    fun loadDialogs() {
        Handler().postDelayed({
            val messagesList1 = ArrayList<MessageModel>()
            val messagesList2= ArrayList<MessageModel>()
            val messagesList3 = ArrayList<MessageModel>()
            val messagesList4 = ArrayList<MessageModel>()
            val messagesList5 = ArrayList<MessageModel>()
            val messagesList6 = ArrayList<MessageModel>()
            val messagesList7 = ArrayList<MessageModel>()
            val messagesList8 = ArrayList<MessageModel>()
            val messagesList9 = ArrayList<MessageModel>()
            val messagesList10 = ArrayList<MessageModel>()
            val messagesList11 = ArrayList<MessageModel>()
            val messagesList12 = ArrayList<MessageModel>()
            val dialogsList = ArrayList<DialogModel>()

            messagesList1.add(MessageModel("Привет!",  "16:14",true))
            messagesList1.add(MessageModel("Привет :3",  "16:15",false))
            messagesList1.add(MessageModel("Чем занимаешься?",  "16:15",true))
            messagesList1.add(MessageModel("Готовлюсь к экзаменам. Ты?",  "16:16",false))
            messagesList1.add(MessageModel("Пишу эти диалоги",  "16:16",true))
            messagesList1.add(MessageModel("Почти закончил с написанием уже)",  "16:17",true))

            messagesList2.add(MessageModel("Самое трудное в программировании, это придумывать диалоги",  "12:14",true))
            messagesList2.add(MessageModel("И имена переменных",  "12:15",false))

            messagesList3.add(MessageModel("Йоу, че каво?",  "11:14",true))
            messagesList3.add(MessageModel("Привет",  "11:23",false))
            messagesList3.add(MessageModel("Хорошо, сам как?",  "11:23",false))
            messagesList3.add(MessageModel("Займи денег до зарплаты?",  "11:24",true))
            messagesList3.add(MessageModel("Напиши в займбот",  "11:25",false))
            messagesList3.add(MessageModel("Там всем одобряют",  "11:25",false))
            messagesList3.add(MessageModel("Ты че е@анутый?",  "11:26",true))
            messagesList3.add(MessageModel("Там over9000% годовых",  "11:26",true))
            messagesList3.add(MessageModel("На@уй мне добровольно в рабство идти",  "11:27",true))
            messagesList3.add(MessageModel("Друг называется",  "11:27",true))
            messagesList3.add(MessageModel("Пошел на@уй",  "11:27",true))


            messagesList4.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList4.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList5.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList5.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList6.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList6.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList7.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList7.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList8.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList8.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList9.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList9.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList10.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList10.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList11.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList11.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            messagesList12.add(MessageModel("Просто сообщение",  "11:27",true))
            messagesList12.add(MessageModel("Просто ответное сообщение",  "11:27",false))

            dialogsList.add(DialogModel("Avdotya", "Raskolnkova", "https://pp.userapi.com/c851216/v851216260/102256/YUsMBVCLGIU.jpg", messagesList1))
            dialogsList.add(DialogModel("Max", "Redkin", "https://pp.userapi.com/c844321/v844321294/8b2cf/QvUtuwmVW7E.jpg", messagesList2))
            dialogsList.add(DialogModel("Ilnur", "Sadreev", "https://pp.userapi.com/c627919/v627919459/1cd2c/f5MlNluwak0.jpg", messagesList3))
            dialogsList.add(DialogModel("Orlando", "Bloom", "https://thumbs.dfs.ivi.ru/storage37/contents/1/2/070d3bdf5de88dba04e2f4ba2bede8.jpg", messagesList4))
            dialogsList.add(DialogModel("Willem", "Dafoe", "https://st.kp.yandex.net/images/actor_iphone/iphone360_9274.jpg", messagesList5))
            dialogsList.add(DialogModel("Vin", "Diesel", "https://gif.cmtt.space/3/paper-media/7c/e9/70/8a6e9b24f617ad.jpg", messagesList6))
            dialogsList.add(DialogModel("Dwayne", "Johnson", "https://24smi.org/public/media/235x307/celebrity/2016/07/08/1467968641-duejn-dzhonson.jpg", messagesList7))
            dialogsList.add(DialogModel("Scarlett", "Johansson", "https://st.kp.yandex.net/images/actor_iphone/iphone360_3903.jpg", messagesList8))
            dialogsList.add(DialogModel("Benedict", "Cumberbatch", "https://24smi.org/public/media/2018/10/23/a728.png", messagesList9))
            dialogsList.add(DialogModel("Leonardo", "DiCaprio", "https://hochu.ua/thumbnails/articles/cropr_640x490/90800_0.jpg", messagesList10))
            dialogsList.add(DialogModel("Samuel", "L. Jackson", "https://memepedia.ru/wp-content/uploads/2017/06/%D1%87%D1%82%D0%BE-%D1%8D%D1%82%D0%BE%D1%82-%D0%BD%D0%B8%D0%B3%D0%B5%D1%80-%D1%81%D0%B5%D0%B1%D0%B5-%D0%BF%D0%BE%D0%B7%D0%B2%D0%BE%D0%BB%D1%8F%D0%B5%D1%82.jpg", messagesList11))
            dialogsList.add(DialogModel("Cassidy", "Jones", "http://www.couturesalonstudios.com/wp-content/uploads/2018/07/7-Cassidy.jpg", messagesList12))

            presenter.dialogsLoaded(dialogsList)
        }, 2000)
    }
}