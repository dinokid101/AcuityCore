package com.acuity.rs.api;


//Generated by the injector on run.

//Extends com/acuity/rs/api/RSCacheableNode
public interface RSNPCComposite extends RSCacheableNode {

	java.lang.String[] getActions();

	short[] getColors();

	int getCombatLevel();

	int getID();

	int[] getModels();

	java.lang.String getName();

	short[] getNewColors();

	short[] getNewTextures();

	com.acuity.rs.api.RS3CopiedNodeTable getProperties();

	short[] getTextures();

	int[] getTransformIDs();

	int getVarpbitIndex();

	int getVarpIndex();

	com.acuity.api.rs.wrappers.peers.composite.NpcComposite getWrapper();

	boolean isClickable();

	boolean isMinimapVisible();

	boolean isVisible();

	void setActions(java.lang.String[] var0);

	void setClickable(boolean var0);

	void setColors(short[] var0);

	void setCombatLevel(int var0);

	void setID(int var0);

	void setMinimapVisible(boolean var0);

	void setModels(int[] var0);

	void setName(java.lang.String var0);

	void setNewColors(short[] var0);

	void setNewTextures(short[] var0);

	void setProperties(com.acuity.rs.api.RS3CopiedNodeTable var0);

	void setTextures(short[] var0);

	void setTransformIDs(int[] var0);

	void setVarpbitIndex(int var0);

	void setVarpIndex(int var0);

	void setVisible(boolean var0);

	void setWrapper(com.acuity.api.rs.wrappers.peers.composite.NpcComposite var0);
}
