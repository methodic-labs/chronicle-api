package com.openlattice.chronicle.data

/**
 *  https://support.twilio.com/hc/en-us/articles/223134347-What-are-the-Possible-SMS-and-MMS-Message-Statuses-and-What-do-They-Mean-
 *  Finalized Message Delivery Status
 *  After a message has been sent, the following final delivery status options are possible:
 */

enum class MessageStatus {
    delivered,
    delivery_unknown,
    failed,
    sent,
    undelivered
}
