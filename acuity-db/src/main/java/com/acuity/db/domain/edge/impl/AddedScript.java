package com.acuity.db.domain.edge.impl;

import com.acuity.db.domain.edge.Edge;

/**
 * Created by Zachary Herridge on 8/7/2017.
 */
public class AddedScript extends Edge{

    public AddedScript(String acuityID, String scriptID) {
        super(acuityID, scriptID);
        this._key = acuityID.split("/")[1] + ":" + scriptID.split("/")[1];
    }

    public AddedScript() {
    }
}
