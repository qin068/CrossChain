import { DenomTrace } from '../ibcdex/denom_trace';
import { BuyOrderBook } from '../ibcdex/buy_order_book';
import { SellOrderBook } from '../ibcdex/sell_order_book';
import { Writer, Reader } from 'protobufjs/minimal';
export declare const protobufPackage = "user.interchange.ibcdex";
/** GenesisState defines the ibcdex module's genesis state. */
export interface GenesisState {
    /** this line is used by starport scaffolding # genesis/proto/state */
    denomTraceList: DenomTrace[];
    /** this line is used by starport scaffolding # genesis/proto/stateField */
    buyOrderBookList: BuyOrderBook[];
    /** this line is used by starport scaffolding # genesis/proto/stateField */
    sellOrderBookList: SellOrderBook[];
    /** this line is used by starport scaffolding # genesis/proto/stateField */
    portId: string;
}
export declare const GenesisState: {
    encode(message: GenesisState, writer?: Writer): Writer;
    decode(input: Reader | Uint8Array, length?: number): GenesisState;
    fromJSON(object: any): GenesisState;
    toJSON(message: GenesisState): unknown;
    fromPartial(object: DeepPartial<GenesisState>): GenesisState;
};
declare type Builtin = Date | Function | Uint8Array | string | number | undefined;
export declare type DeepPartial<T> = T extends Builtin ? T : T extends Array<infer U> ? Array<DeepPartial<U>> : T extends ReadonlyArray<infer U> ? ReadonlyArray<DeepPartial<U>> : T extends {} ? {
    [K in keyof T]?: DeepPartial<T[K]>;
} : Partial<T>;
export {};
