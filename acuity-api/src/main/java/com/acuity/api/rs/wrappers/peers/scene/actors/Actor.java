package com.acuity.api.rs.wrappers.peers.scene.actors;

import com.acuity.api.AcuityInstance;
import com.acuity.api.annotations.ClientInvoked;
import com.acuity.api.rs.interfaces.Locatable;
import com.acuity.api.rs.interfaces.Nameable;
import com.acuity.api.rs.utils.Scene;
import com.acuity.api.rs.wrappers.common.locations.FineLocation;
import com.acuity.api.rs.wrappers.common.locations.SceneLocation;
import com.acuity.api.rs.wrappers.common.locations.WorldLocation;
import com.acuity.api.rs.wrappers.common.locations.screen.ScreenLocationShape;
import com.acuity.api.rs.wrappers.peers.rendering.Model;
import com.acuity.api.rs.wrappers.peers.rendering.Renderable;
import com.acuity.api.rs.wrappers.peers.rendering.bounding_boxes.AxisAlignedBoundingBox;
import com.acuity.api.rs.wrappers.peers.structures.NodeLinkedList;
import com.acuity.rs.api.RSActor;
import com.acuity.rs.api.RSNodeLinkedList;
import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Eclipseop.
 * Date: 6/8/2017.
 */
public abstract class Actor extends Renderable implements Locatable, Nameable {

    private static final Logger logger = LoggerFactory.getLogger(Actor.class);

    private RSActor rsActor;

    @ClientInvoked
    public Actor(@NotNull final RSActor peer) {
        super(peer);
        this.rsActor = Preconditions.checkNotNull(peer);
    }

    @Override
    public Optional<Model> getCachedModel() {
        FineLocation fineLocation = getFineLocation();
        return super.getCachedModel()
                .map(model -> model.place(fineLocation.getFineX(), fineLocation.getFineY()))
                .map(model -> model.rotateTo(getOrientation()));
    }

    public int getOrientation() {
        return rsActor.getOrientation();
    }

    public int getTargetIndex() {
        return rsActor.getTargetIndex();
    }


    public int getAnimation() {
        return rsActor.getAnimation();
    }

    public Optional<NodeLinkedList> getHealthBars() {
        return Optional.ofNullable(rsActor.getHealthBars()).map(RSNodeLinkedList::getWrapper);
    }

    public int getIdlePoseAnimation() {
        return rsActor.getIdlePoseAnimation();
    }

    //null when no overhead text
    @Nullable
    public String getOverhead() {
        //// TODO: 7/12/2017  return rsActor.getOverhead();
        return null;
    }

    public int getPoseAnimation() {
        return rsActor.getPoseAnimation();
    }

    public int getSpellAnimationID() {
        // TODO: 7/12/2017  return rsActor.getSpellAnimationID();
        return -1;
    }

    public int[] getHitsplatCycles() {
        return rsActor.getHitsplatCycles();
    }

    public boolean isAnimating() {
        return getAnimation() != getIdlePoseAnimation();
    }

    public FineLocation getFineLocation(){
        return new FineLocation(rsActor.getFineX(), rsActor.getFineY(), Scene.getPlane());
    }

    public SceneLocation getSceneLocation(){
        return getFineLocation().getSceneLocation();
    }

    @Override
    public WorldLocation getWorldLocation() {
        return getSceneLocation().getWorldLocation();
    }

    @NotNull
    public RSActor getRsActor() {
        return rsActor;
    }

    @Override
    public Supplier<Optional<ScreenLocationShape>> getScreenTargetSupplier() {
        if (!AcuityInstance.getSettings().isModelInteractionsEnabled()){
            return () -> getBoundingBox().map(AxisAlignedBoundingBox::getScreenTargetSupplier).map(Supplier::get).orElseGet(Optional::empty);
        }
        return () -> getCachedModel().map(Model::getScreenTargetSupplier).map(Supplier::get).orElseGet(Optional::empty);
    }
}
