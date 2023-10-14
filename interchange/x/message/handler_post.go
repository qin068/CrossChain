package message

import (
	sdk "github.com/cosmos/cosmos-sdk/types"
	"github.com/user/interchange/x/message/keeper"
	"github.com/user/interchange/x/message/types"
)

func handleMsgCreatePost(ctx sdk.Context, k keeper.Keeper, msg *types.MsgCreatePost) (*sdk.Result, error) {
	k.CreatePost(ctx, *msg)

	return &sdk.Result{Events: ctx.EventManager().ABCIEvents()}, nil
}
