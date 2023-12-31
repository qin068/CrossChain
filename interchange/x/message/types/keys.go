package types

const (
	// ModuleName defines the module name
	ModuleName = "message"

	// StoreKey defines the primary module store key
	StoreKey = ModuleName

	// RouterKey is the message route for slashing
	RouterKey = ModuleName

	// QuerierRoute defines the module's query routing key
	QuerierRoute = ModuleName

	// MemStoreKey defines the in-memory store key
	MemStoreKey = "mem_message"

	// this line is used by starport scaffolding # ibc/keys/name
	PostKey = "Post-value-"

	// PostCountKey defines the post count store key
	PostCountKey = "Post-count-"
)

// this line is used by starport scaffolding # ibc/keys/port

func KeyPrefix(p string) []byte {
	return []byte(p)
}
