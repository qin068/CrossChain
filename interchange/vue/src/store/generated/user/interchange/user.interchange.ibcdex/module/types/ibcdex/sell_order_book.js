/* eslint-disable */
import { OrderBook } from '../ibcdex/order';
import { Writer, Reader } from 'protobufjs/minimal';
export const protobufPackage = 'user.interchange.ibcdex';
const baseSellOrderBook = { creator: '', index: '', amountDenom: '', priceDenom: '' };
export const SellOrderBook = {
    encode(message, writer = Writer.create()) {
        if (message.creator !== '') {
            writer.uint32(10).string(message.creator);
        }
        if (message.index !== '') {
            writer.uint32(18).string(message.index);
        }
        if (message.amountDenom !== '') {
            writer.uint32(26).string(message.amountDenom);
        }
        if (message.priceDenom !== '') {
            writer.uint32(34).string(message.priceDenom);
        }
        if (message.book !== undefined) {
            OrderBook.encode(message.book, writer.uint32(42).fork()).ldelim();
        }
        return writer;
    },
    decode(input, length) {
        const reader = input instanceof Uint8Array ? new Reader(input) : input;
        let end = length === undefined ? reader.len : reader.pos + length;
        const message = { ...baseSellOrderBook };
        while (reader.pos < end) {
            const tag = reader.uint32();
            switch (tag >>> 3) {
                case 1:
                    message.creator = reader.string();
                    break;
                case 2:
                    message.index = reader.string();
                    break;
                case 3:
                    message.amountDenom = reader.string();
                    break;
                case 4:
                    message.priceDenom = reader.string();
                    break;
                case 5:
                    message.book = OrderBook.decode(reader, reader.uint32());
                    break;
                default:
                    reader.skipType(tag & 7);
                    break;
            }
        }
        return message;
    },
    fromJSON(object) {
        const message = { ...baseSellOrderBook };
        if (object.creator !== undefined && object.creator !== null) {
            message.creator = String(object.creator);
        }
        else {
            message.creator = '';
        }
        if (object.index !== undefined && object.index !== null) {
            message.index = String(object.index);
        }
        else {
            message.index = '';
        }
        if (object.amountDenom !== undefined && object.amountDenom !== null) {
            message.amountDenom = String(object.amountDenom);
        }
        else {
            message.amountDenom = '';
        }
        if (object.priceDenom !== undefined && object.priceDenom !== null) {
            message.priceDenom = String(object.priceDenom);
        }
        else {
            message.priceDenom = '';
        }
        if (object.book !== undefined && object.book !== null) {
            message.book = OrderBook.fromJSON(object.book);
        }
        else {
            message.book = undefined;
        }
        return message;
    },
    toJSON(message) {
        const obj = {};
        message.creator !== undefined && (obj.creator = message.creator);
        message.index !== undefined && (obj.index = message.index);
        message.amountDenom !== undefined && (obj.amountDenom = message.amountDenom);
        message.priceDenom !== undefined && (obj.priceDenom = message.priceDenom);
        message.book !== undefined && (obj.book = message.book ? OrderBook.toJSON(message.book) : undefined);
        return obj;
    },
    fromPartial(object) {
        const message = { ...baseSellOrderBook };
        if (object.creator !== undefined && object.creator !== null) {
            message.creator = object.creator;
        }
        else {
            message.creator = '';
        }
        if (object.index !== undefined && object.index !== null) {
            message.index = object.index;
        }
        else {
            message.index = '';
        }
        if (object.amountDenom !== undefined && object.amountDenom !== null) {
            message.amountDenom = object.amountDenom;
        }
        else {
            message.amountDenom = '';
        }
        if (object.priceDenom !== undefined && object.priceDenom !== null) {
            message.priceDenom = object.priceDenom;
        }
        else {
            message.priceDenom = '';
        }
        if (object.book !== undefined && object.book !== null) {
            message.book = OrderBook.fromPartial(object.book);
        }
        else {
            message.book = undefined;
        }
        return message;
    }
};
