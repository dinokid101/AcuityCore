package com.acuity.rs.api;


//Generated by the injector on run.

//Extends java/lang/Object
public interface RSAudioInstrument {

	int[] getOscillatorPitch();

	com.acuity.rs.api.RSAudioEnvelope getPitchEnvelope();

	void setOscillatorPitch(int[] var0);

	void setPitchEnvelope(com.acuity.rs.api.RSAudioEnvelope var0);
}
