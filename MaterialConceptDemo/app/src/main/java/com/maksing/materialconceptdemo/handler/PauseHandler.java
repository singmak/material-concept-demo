package com.maksing.materialconceptdemo.handler;

/**
 * Created by maksing on 6/12/14.
 * It's a nice idea from http://stackoverflow.com/questions/8040280/how-to-handle-handler-messages-when-activity-fragment-is-mPaused
 */

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Vector;

/**
 * Message Handler class that supports buffering up of messages when the
 * activity is mPaused i.e. in the background.
 */
public abstract class PauseHandler extends Handler {

    public PauseHandler() {
        super(Looper.getMainLooper());
    }

    /**
     * Message Queue Buffer
     */
    private final Vector<Message> mMessageQueueBuffer = new Vector<Message>();

    /**
     * Flag indicating the pause state
     */
    private boolean mPaused;

    /**
     * Resume the handler
     */
    public final void resume() {
        mPaused = false;

        while (mMessageQueueBuffer.size() > 0) {
            final Message msg = mMessageQueueBuffer.elementAt(0);
            mMessageQueueBuffer.removeElementAt(0);
            sendMessage(msg);
        }
    }

    /**
     * Pause the handler
     */
    public final void pause() {
        mPaused = true;
    }

    /**
     * Notification that the message is about to be stored as the activity is
     * mPaused. If not handled the message will be saved and replayed when the
     * activity resumes.
     *
     * @param message
     *            the message which optional can be handled
     * @return true if the message is to be stored
     */
    protected abstract boolean storeMessage(Message message);

    /**
     * Notification message to be processed. This will either be directly from
     * handleMessage or played back from a saved message when the activity was
     * paused.
     *
     * @param message
     *            the message to be handled
     */
    protected abstract void processMessage(Message message);

    /** {@inheritDoc} */
    @Override
    public final void handleMessage(Message msg) {
        if (mPaused) {
            if (storeMessage(msg)) {
                Message msgCopy = new Message();
                msgCopy.copyFrom(msg);
                mMessageQueueBuffer.add(msgCopy);
            }
        } else {
            processMessage(msg);
        }
    }

}
