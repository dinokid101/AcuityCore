package com.acuity.rs.api;


//Generated by the injector on run.

//Extends com/acuity/rs/api/RSCacheableNode
public interface RSKitDefinition extends RSCacheableNode {

	int getBodyPartID();

	int[] getModelIDs();

	int[] getModels();

	short[] getRecolorToFind();

	short[] getRecolorToReplace();

	short[] getRetextureToFind();

	short[] getRetextureToReplace();

	com.acuity.rs.api.RSModelComposite invokeGetModelData();

	boolean invokeReady();

	boolean isNonSelectable();

	void setBodyPartID(int var0);

	void setModelIDs(int[] var0);

	void setModels(int[] var0);

	void setNonSelectable(boolean var0);

	void setRecolorToFind(short[] var0);

	void setRecolorToReplace(short[] var0);

	void setRetextureToFind(short[] var0);

	void setRetextureToReplace(short[] var0);
}
