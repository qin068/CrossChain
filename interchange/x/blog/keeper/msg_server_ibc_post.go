package keeper

import (
	"context"
	// "crypto/aes"
	// "crypto/cipher"
	// "crypto/rand"
	// "encoding/base64"
	// "io"

	sdk "github.com/cosmos/cosmos-sdk/types"
	clienttypes "github.com/cosmos/cosmos-sdk/x/ibc/core/02-client/types"
	"github.com/user/interchange/x/blog/types"
)

// func AesEncryptCFB(origData []byte, key []byte) (encrypted []byte) {
// 	block, err := aes.NewCipher(key)
// 	if err != nil {
// 		panic(err)
// 	}
// 	encrypted = make([]byte, aes.BlockSize+len(origData))
// 	iv := encrypted[:aes.BlockSize]
// 	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
// 		panic(err)
// 	}
// 	stream := cipher.NewCFBEncrypter(block, iv)
// 	stream.XORKeyStream(encrypted[aes.BlockSize:], origData)
// 	return encrypted
// }

func (k msgServer) SendIbcPost(goCtx context.Context, msg *types.MsgSendIbcPost) (*types.MsgSendIbcPostResponse, error) {
	ctx := sdk.UnwrapSDKContext(goCtx)
	// key := []byte("ABCDEFGHIJKLMNOP")
	// origData := []byte(msg.Content)
	// TODO: logic before transmitting the packet

	// Construct the packet
	var packet types.IbcPostPacketData

	packet.Title = msg.Title
	// encrypted := AesEncryptCFB(origData, key)
	// base64msg := base64.StdEncoding.EncodeToString(encrypted)
	packet.Content = msg.Content
	packet.Creator = msg.Sender
	// Transmit the packet
	err := k.TransmitIbcPostPacket(
		ctx,
		packet,
		msg.Port,
		msg.ChannelID,
		clienttypes.ZeroHeight(),
		msg.TimeoutTimestamp,
	)
	if err != nil {
		return nil, err
	}

	return &types.MsgSendIbcPostResponse{}, nil
}
