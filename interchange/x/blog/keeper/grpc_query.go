package keeper

import (
	"github.com/user/interchange/x/blog/types"
)

var _ types.QueryServer = Keeper{}
