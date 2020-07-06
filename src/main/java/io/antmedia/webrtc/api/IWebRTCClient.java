package io.antmedia.webrtc.api;

import java.nio.ByteBuffer;
import java.util.List;

import org.webrtc.IceCandidate;
import org.webrtc.NaluIndex;
import org.webrtc.SessionDescription;

import io.antmedia.statistic.type.WebRTCAudioSendStats;
import io.antmedia.statistic.type.WebRTCVideoSendStats;

public interface IWebRTCClient {
	
	
	public void setWebRTCAdaptor(IWebRTCAdaptor webRTCAdaptor);
	
	
	/**
	 * Send video packet to connected client
	 * @param videoPacket
	 * @param isKeyFrame
	 * @param naluIndices 
	 * @param trackIndex 
	 */
	public void sendVideoPacket(ByteBuffer videoPacket, boolean isKeyFrame, long timestamp, int frameRotation, List<NaluIndex> naluIndices, String trackId);
	
	/**
	 * Send audio packet to connected client
	 * @param audioPacket
	 */
	public void sendAudioPacket(ByteBuffer audioPacket, long timestamp);
	
	
	
	public int getTargetBitrate();
	
	
	public void start();
	
	public void setRemoteDescription(SessionDescription sdp);
	
	public void addIceCandidate(IceCandidate iceCandidate);
	
	public void setVideoResolution(int width, int height);


	public void setWebRTCMuxer(IWebRTCMuxer webRTCMuxer);
	
	public IWebRTCMuxer getWebRTCMuxer();
	
	public void stop();


	/**
	 * Returns the time in milliseconds between the time when start function is called 
	 * and the time when streaming is started
	 * @return the time in milliseconds
	 * or -1 if timing is not available yet
	 */
	long getTimeToStartStreaming();
	
	/**
	 * Returns the time in milliseconds between the time when stop function is called 
	 * and the time when streaming is fully stopped
	 * @return the time in milliseconds
	 * or -1 if timing is not available yet
	 */
	long getTimeToStop();
	
	/**
	 * Return the period of send video period in milliseconds
	 * @return
	 */
	float getVideoFrameSentPeriod();
	
	/**
	 * Return the period of send audio period in milliseconds
	 * @return
	 */
	float getAudioFrameSentPeriod();
	
	/** 
	 * @return the number of times video packet send called
	 */
	long getSendVideoPacketCallCount();
	
	/**
	 * @return number of times audio packet send called
	 */
	long getSendAudioPacketCallCount();
	
	/**
	 * @return low level video stats
	 */
	public WebRTCVideoSendStats getVideoStats();
	
	/**
	 * @return low level audio stats
	 */
	public WebRTCAudioSendStats getAudioStats();

	/**
	 * It's called when there is excessive bandwidth than the current one
	 */
	public void increaseExcessiveBandwidthCount();
	
	/**
	 * Number of consecutive calls of increaseExcessiveBandwidthCount
	 * @return
	 */
	public int getExcessiveBandwidthCount();
	
	/**
	 * Reset the excessive bandwidth count
	 */
	public void resetExcessiveBandwidthCount();


	/**
	 * Return the round trip time measurement 
	 * @return
	 */
	int getRttMeasurement();


	/**
	 * Returns the packetloss rate
	 * @return
	 */
	int getPacketLoss();

	/**
	 * 
	 * @param tryCountBeforeSwitchback
	 */
	void setTryCountBeforeSwitchBack(int tryCountBeforeSwitchback);
	
	/**
	 * 
	 * @return decrease
	 */
	int getTryCountBeforeSwitchBack();


	/**
	 * Cache current channel parameters like round trip time and packet loss
	 */
	public void cacheChannelParameters();
	
	/**
	 * @return cached packet loss
	 */
	int getCachedPacketLoss();
	
	/**
	 * 
	 * @return cache rtt measurement
	 */
	int getCachedRttMeasurement();
	
	/**
	 * 
	 * @return stream resolution forced value
	 * If it's automatic, it returns 0
	 */
	public int getForceStreamHeight();
	
	/**
	 * 
	 * @return stream resolution current value
	 */
	public void forceStreamQuality(int streamHeight);
}
