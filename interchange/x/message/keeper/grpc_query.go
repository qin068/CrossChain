package keeper

import (
	"github.com/user/interchange/x/message/types"
)

var _ types.QueryServer = Keeper{}
