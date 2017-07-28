package com.acuity.inject.callbacks;

import com.acuity.api.AcuityInstance;
import com.acuity.api.Events;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.events.impl.ActionEvent;
import com.acuity.api.rs.events.impl.GameStateChangeEvent;
import com.acuity.api.rs.events.impl.GameTickEvent;
import com.acuity.api.rs.events.impl.MouseRecorderUpdateEvent;
import com.acuity.api.rs.events.impl.drawing.GameDrawEvent;
import com.acuity.api.rs.events.impl.drawing.InGameDrawEvent;
import com.acuity.api.rs.utils.Game;
import com.acuity.rs.api.RSAxisAlignedBoundingBox;
import com.acuity.rs.api.RSConnection;
import com.acuity.rs.api.RSModel;
import com.acuity.rs.api.RSRenderable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * Created by Zachary Herridge on 6/7/2017.
 */
public class Callbacks {

    private static final Logger logger = LoggerFactory.getLogger(Callbacks.class);

    @ClientInvoked
    public static void fieldUpdating(String name, int index, Object instance) {
        logger.debug("Field Updating: '{}' with index={} and instance={}", name, index, instance);
        switch (name) {

        }
    }

    @ClientInvoked
    public static void fieldUpdated(String name, int index, Object instance) {
        logger.debug("Field Updated: '{}' with index={} and instance={}", name, index, instance);
        switch (name) {
            case "gameState":
                Events.getRsEventBus().post(new GameStateChangeEvent(AcuityInstance.getClient().getGameState()));
                break;
            case "mouseYHistory":
                try {
                    int lastX = AcuityInstance.getClient().getRsClient().getMouseRecorder().getMouseXHistory()[index];
                    int lastY = AcuityInstance.getClient().getRsClient().getMouseRecorder().getMouseYHistory()[index];
                    Events.getRsEventBus().post(new MouseRecorderUpdateEvent(System.currentTimeMillis(), lastX, lastY));
                }
                catch (Exception e){

                }
                break;

        }
    }

    @ClientInvoked
    public static void processActionCallback(int arg2, int arg3, int opcode, int arg1, String action, String target, int clickX, int clickY){
        Events.getRsEventBus().post(new ActionEvent(opcode, arg1, arg2, arg3, action, target, clickX, clickY));
    }

    @ClientInvoked
    public static void tick() {
        GameTickEvent.incrementTick();
        Events.getRsEventBus().post(GameTickEvent.INSTANCE);
    }

    @ClientInvoked
    public static void queueForWriteCallback(RSConnection connectionInstance, byte[] writeBuffer, int offset, int length){
        logger.trace("Queued for write buffer={} offset={} length={}", writeBuffer == null ? "null" : "[" + writeBuffer.length + "]", offset, length);
    }

    @ClientInvoked
    public static void addAxisAlignedBoundingBoxCallback(RSModel rsModel, int i, int i2, int i3, int i4){
        System.out.println();
    }

    @ClientInvoked
    public static void generateLegacy2DBoundingBoxCallback(int i, int i2, int i3, int i4, int i5, int i6, int i7){
        System.out.println();
    }

    @ClientInvoked
    public static void aabbMouseTargetCalcCallBack(RSModel rsModel, int i, int i2, int i3){
        try {
            Field ah = rsModel.getClass().getField("ah");
            ah.setBoolean(rsModel, true);
            System.out.println("set");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @ClientInvoked
    public static void boundingBoxUpdated(RSRenderable rsRenderable, RSAxisAlignedBoundingBox rsAxisAlignedBoundingBox){
    }

    @ClientInvoked
    public static void drawCallback(Image image) {
        try {
            if (Game.State.IN_GAME.isCurrent()) {
                Events.getRsEventBus().post(new InGameDrawEvent(image));
            }
            else {
                Events.getRsEventBus().post(new GameDrawEvent(image));
            }
        }
        catch (Exception e){
            logger.error("Error during drawing.", e);
        }
    }
}
