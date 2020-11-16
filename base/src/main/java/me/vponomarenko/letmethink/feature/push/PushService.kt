package me.vponomarenko.letmethink.feature.push



/**
 * Author: Valery Ponomarenko
 * Date: 12.04.2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

//class PushService : FirebaseMessagingService() {
//
//    override fun onMessageReceived(message: RemoteMessage) {
//        handleMessage(message)
//    }
//
//    private fun handleMessage(message: RemoteMessage) {
//        val intent = MainActivity.newIntentForUpdate(this)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent
//                .getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
//
//        val notification = buildNotification(
//                message,
//                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
//                pendingIntent
//        )
//
//        with(getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel(
//                        getString(R.string.default_notification_channel_id),
//                        getString(R.string.updates),
//                        NotificationManager.IMPORTANCE_DEFAULT
//                ).apply {
//                    createNotificationChannel(this)
//                }
//            }
//
//            notify(0, notification)
//        }
//    }
//
//    private fun buildNotification(message: RemoteMessage, soundUri: Uri, intent: PendingIntent) =
//            NotificationCompat
//                    .Builder(this, getString(R.string.default_notification_channel_id))
//                    .setSmallIcon(R.drawable.ic_bulb)
//                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
//                    .setContentTitle(message.notification?.title ?: "")
//                    .setContentText(message.notification?.body ?: "")
//                    .setAutoCancel(true)
//                    .setSound(soundUri)
//                    .setContentIntent(intent)
//                    .build()
//
//}