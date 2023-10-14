package keeper

import (
	"github.com/user/interchange/x/ibcdex/types"
)

var _ types.QueryServer = Keeper{}
